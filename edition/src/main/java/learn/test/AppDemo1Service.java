package learn.test;

import java.text.ParseException;

public interface AppDemo1Service {

	void getStockCheckTaskDetail(String jsonString) throws ParseException;

	void getStockCheckTaskList(String jsonString) throws ParseException;

	void getEnterStockTaskList(String jsonString) throws ParseException;

	void getEnterStockTaskDetail(String jsonString) throws ParseException;

	void updateEnterStockStatus(String jsonString);

	void updateCheckStockStatus(String jsonString);

	void getStockTaskStatistics(String jsonString);

}
