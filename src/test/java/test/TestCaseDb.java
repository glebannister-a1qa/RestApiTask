package test;

import app.model.DbModel;
import app.queries.Queries;
import framework.utils.DbUtil;
import framework.utils.FileUtil;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDb {
    @Test
    public void databaseTest() {

        DbModel dbModel = new DbModel();
        dbModel.setUrl(PropertyReader.getDbData("dbUrl"));
        dbModel.setLogin(PropertyReader.getDbData("dbUsername"));
        dbModel.setPassword(PropertyReader.getDbData("dbPassword"));

        LogUtil.step(
                1,
                "Step to make query for every test get project and time of testing sort by" +
                "project and test"
        );
        FileUtil.writeToFile(
                DbUtil.select(dbModel, Queries.MIN_WORK_TIME_FOR_EVERY_TEST_QUERY),
                PropertyReader.getDataValue("pathToStep1result")
        );
        Assert.assertTrue(
                FileUtil.isFileWithQueryResultNotEmpty(PropertyReader.getDataValue("pathToStep1result")),
                "File is empty"
        );

        LogUtil.step(
                2,
                "Step to make query for all projects with amount of their tests"
        );
        FileUtil.writeToFile(
                DbUtil.select(dbModel, Queries.ALL_PROJECTS_WITH_AMOUNT_OF_TESTS_QUERY),
                PropertyReader.getDataValue("pathToStep2result")
        );
        Assert.assertTrue(
                FileUtil.isFileWithQueryResultNotEmpty(PropertyReader.getDataValue("pathToStep2result")),
                "File is empty"
        );

        LogUtil.step(
                3,
                "Step to make query for all tests later then 2015-11-07 sort by projects and" +
                " tests"
        );
        FileUtil.writeToFile(
                DbUtil.select(dbModel, Queries.ALL_TESTS_LATER_THAN_2015_11_07_QUERY),
                PropertyReader.getDataValue("pathToStep3result")
        );
        Assert.assertTrue(
                FileUtil.isFileWithQueryResultNotEmpty(PropertyReader.getDataValue("pathToStep3result")),
                "File is empty"
        );

        LogUtil.step(
                4,
                "Step to make query for all amount of test on chrome and firefox browsers"
        );
        FileUtil.writeToFile(
                DbUtil.select(dbModel, Queries.ALL_TESTS_IN_CHROME_AND_FIREFOX_QUERY),
                PropertyReader.getDataValue("pathToStep4result")
        );
        Assert.assertTrue(
                FileUtil.isFileWithQueryResultNotEmpty(PropertyReader.getDataValue("pathToStep4result")),
                "File is empty"
        );
    }

}
