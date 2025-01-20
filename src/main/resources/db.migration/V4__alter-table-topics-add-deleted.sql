
ALTER TABLE topics ADD deleted tinyint;
UPDATE topics SET deleted = 0;
