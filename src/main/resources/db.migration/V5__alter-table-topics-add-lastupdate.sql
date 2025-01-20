
ALTER TABLE topics ADD lastupdata VARCHAR(20);
UPDATE topics SET lastupdata = timestamp;
