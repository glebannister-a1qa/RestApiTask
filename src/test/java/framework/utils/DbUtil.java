package framework.utils;

import app.constants.RegExpConstants;
import app.model.DbModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {

    public static List<String> select(DbModel model, String query) {

        List<String> listColumnNames = new ArrayList<>();
        List<String> listData = new ArrayList<>();
        List<String> resultList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                model.getUrl(),
                model.getLogin(),
                model.getPassword()
        );
             Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery(query)) {
            listColumnNames = getHeaders(model,query);
            int cols = result.getMetaData().getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= cols; i++) {
                    listData.add(String.valueOf(result.getObject(i)));
                    if (i < cols){
                        listData.add(RegExpConstants.semicolon);
                    }
                }
                listData.add(System.lineSeparator());
            }
        } catch (SQLException throwables) {
            LogUtil.error(throwables.toString());
        }
        resultList.addAll(listColumnNames);
        resultList.addAll(listData);
        return resultList;
    }

    private static List<String> getHeaders(DbModel model, String query){

        List<String> columnHeaders = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                model.getUrl(),
                model.getLogin(),
                model.getPassword()
        );
             Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery(query)){
            int cols = result.getMetaData().getColumnCount();
            ResultSetMetaData rsmd = result.getMetaData();

            while (result.next()) {
                for (int i = 1; i <= cols; i++) {
                    columnHeaders.add(rsmd.getColumnLabel(i));
                    if (i < cols){
                        columnHeaders.add(RegExpConstants.semicolon);
                    }
                }
                columnHeaders.add(System.lineSeparator());
                break;
            }
        } catch (SQLException throwables){
            LogUtil.error(throwables.toString());
        }
        return columnHeaders;
    }

}
