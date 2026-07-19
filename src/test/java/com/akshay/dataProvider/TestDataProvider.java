package com.akshay.dataProvider;

import com.akshay.constants.FrameworkConstants;
import com.akshay.utilities.ConfigReader;
import com.akshay.utilities.ExcelUtils;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
    private static final String LOGIN_DATA =
            ConfigReader.getProperty(FrameworkConstants.TEST_DATA_PATH);

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ExcelUtils.getTestData(LOGIN_DATA,"Login");
    }
}