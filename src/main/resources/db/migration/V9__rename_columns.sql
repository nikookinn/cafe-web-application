ALTER TABLE categories RENAME COLUMN imageUrl TO image_url;

ALTER TABLE categories RENAME COLUMN isActive TO is_active;

ALTER TABLE categories RENAME COLUMN menuId TO menu_id;

ALTER TABLE contact_information RENAME COLUMN cafeName TO cafe_name;

ALTER TABLE contact_information RENAME COLUMN phoneNumber TO phone_number;

ALTER TABLE contact_information RENAME COLUMN workingHours TO working_hours;

ALTER TABLE contact_information RENAME COLUMN websiteImageUrl TO website_image_url;

ALTER TABLE gallery RENAME COLUMN imageId TO image_id;

ALTER TABLE gallery RENAME COLUMN imageUrl TO image_url;

ALTER TABLE menuitems RENAME COLUMN itemId TO item_id;

ALTER TABLE menuitems RENAME COLUMN imageUrl TO image_url;

ALTER TABLE menuitems RENAME COLUMN categoryId TO category_id;

ALTER TABLE menuitems RENAME COLUMN isActive TO is_active;

ALTER TABLE menus RENAME COLUMN menuId TO menu_id;

ALTER TABLE menus RENAME COLUMN imageUrl TO image_url;

ALTER TABLE menus RENAME COLUMN isActive TO is_active;