package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        Timestamp timestamp = new Timestamp(parameter.getMillis());
        ps.setTimestamp(i, timestamp);
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        DateTime dateTime = new DateTime(timestamp);
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        DateTime dateTime = new DateTime(timestamp);
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        DateTime dateTime = new DateTime(timestamp);
        return dateTime;
    }
}
