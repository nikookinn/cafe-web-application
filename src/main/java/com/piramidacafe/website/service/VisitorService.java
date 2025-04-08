package com.piramidacafe.website.service;

import com.piramidacafe.website.model.DailyVisit;
import com.piramidacafe.website.repository.DailyVisitRepository;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private static final String VISITOR_COOKIE_NAME = "daily_visitor";
    private AtomicInteger visitCounter = new AtomicInteger(0);

    private final DailyVisitRepository dailyVisitRepository;

    public boolean incrementVisitorCount(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies !=null){
            for (Cookie cookie : cookies) {
                if (VISITOR_COOKIE_NAME.equals(cookie.getName())){
                    return false;
                }
            }
        }
        visitCounter.incrementAndGet();

        Cookie visitorCookie = new Cookie(VISITOR_COOKIE_NAME,"visited");
        visitorCookie.setMaxAge(12 * 60 * 60);
        visitorCookie.setPath("/");
        response.addCookie(visitorCookie);

        return true;
    }
    @Scheduled(cron = "0 59 23 * * *")
    public void saveDailyVisitCount(){
        saveTodayVisitCount();
    }
    @PreDestroy
    public void saveVisitCountOnShutdown() {
        saveTodayVisitCount();
    }

    private void saveTodayVisitCount() {
        int count = visitCounter.get();

        if (count>0){
            LocalDate today = LocalDate.now();
            Optional<DailyVisit> existingVisit = dailyVisitRepository.findByVisitDate(today);
            if (existingVisit.isPresent()){
                DailyVisit visit = existingVisit.get();
                visit.setVisitorCount(visit.getVisitorCount()+count);
                dailyVisitRepository.save(visit);
            }else {
                DailyVisit newVisit = new DailyVisit();
                newVisit.setVisitorCount(count);
                dailyVisitRepository.save(newVisit);
            }
            visitCounter.set(0);
        }
    }
    public int getTodayVisitorCount() {
        return visitCounter.get();
    }


    public Integer getDailyVisitors() {
        LocalDate today = LocalDate.now();
        Optional<DailyVisit> todayVisitors = dailyVisitRepository.findByVisitDate(today);
        if (todayVisitors.isEmpty()){
            return getTodayVisitorCount();
        }
        DailyVisit visit = todayVisitors.get();
        return visit.getVisitorCount() + getTodayVisitorCount();
    }

    public Integer getMonthlyVisitors() {
        YearMonth currentMonth = YearMonth.now();
        Integer monthlyVisitors = dailyVisitRepository.getTotalVisitsByMonth(currentMonth.getYear(), currentMonth.getMonthValue());
        if (monthlyVisitors==null){
            return 0;
        }
        return monthlyVisitors + getTodayVisitorCount();
    }

    public Integer getYearlyVisitors() {
        LocalDate today = LocalDate.now();
        Integer yearlyVisitors = dailyVisitRepository.getTotalVisitsByYear(today.getYear());
        if (yearlyVisitors==null){
            return 0;
        }
        return yearlyVisitors + getTodayVisitorCount();
    }
}
