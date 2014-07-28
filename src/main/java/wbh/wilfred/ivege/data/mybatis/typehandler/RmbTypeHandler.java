package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import wbh.wilfred.ivege.model.Rmb;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RmbTypeHandler extends BaseTypeHandler<Rmb> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Rmb parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.getJiao());
    }

    @Override
    public Rmb getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new Rmb(rs.getLong(columnName));
    }

    @Override
    public Rmb getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Rmb(rs.getLong(columnIndex));
    }

    @Override
    public Rmb getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Rmb(cs.getLong(columnIndex));
    }
}
