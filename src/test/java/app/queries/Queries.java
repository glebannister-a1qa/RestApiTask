package app.queries;

public class Queries {
    public static final String MIN_WORK_TIME_FOR_EVERY_TEST_QUERY = "select project.name as PROJECT, test.name as TEST," +
            " test.end_time - test.start_time MIN_WORKING_TIME from project join test on test.project_id = project.id " +
            "order by project.name, test.name";
    public static final String ALL_PROJECTS_WITH_AMOUNT_OF_TESTS_QUERY = "select project.name as PROJECT," +
            "count(test.name) TEST_COUNT from project inner join test where project.id = test.project_id group by" +
            " project.name";
    public static final String ALL_TESTS_LATER_THAN_2015_11_07_QUERY = "select project.name as PROJECT, test.name TEST," +
            "test.start_time 'DATE' from project join test WHERE project.id = test.project_id and test.start_time >" +
            " 2015-11-07 order by project.name, test.name";
    public static final String ALL_TESTS_IN_CHROME_AND_FIREFOX_QUERY = "select count(test.browser) as BROWSERS from test" +
            " where test.browser = 'chrome' union select count(test.browser) from test where test.browser = 'firefox'";
}
