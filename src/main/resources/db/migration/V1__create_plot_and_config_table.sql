-- Create the plot table
CREATE TABLE IF NOT EXISTS PLOT
(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
longitude VARCHAR(10),
latitude VARCHAR(10),
last_updated_date DATE,
next_irrigation_date DATE,
crop VARCHAR(255)
);

--Create plot config and define relationship
CREATE TABLE IF NOT EXISTS Plot_Slot (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  irrigation_time TIME,
  water_required DOUBLE,
  status VARCHAR(20),
  plot_id int NOT NULL,
  CONSTRAINT FK_PlotConfig_Plot FOREIGN KEY (plot_id)
    REFERENCES Plot (id)
);


