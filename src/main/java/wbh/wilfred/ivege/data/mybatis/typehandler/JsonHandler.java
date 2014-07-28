package wbh.wilfred.ivege.data.mybatis.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonHandler extends BaseTypeHandler<Object> {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String SPLIT = "\\";
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Object parameter,
                                    JdbcType jdbcType) throws SQLException {
        String json;
        try {
            json = objectMapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
        ps.setString(i, json + SPLIT + parameter.getClass().getName());
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws
            SQLException {

        return parse(rs.getString(columnName));
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws
            SQLException {
        return parse(rs.getString(columnIndex));
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws
            SQLException {
        return parse(cs.getString(columnIndex));
    }

    private Object parse(String json) {
        if (json == null) {
            return null;
        }
        int index = json.lastIndexOf(SPLIT);
        if (index == -1) {
            return null;
        }
        String jsonContent = json.substring(0, index);
        String className = json.substring(index + 1);
        Class _class;
        try {
            _class = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        Object result;
        try {
            result = objectMapper.readValue(jsonContent, _class);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return result;
    }
}
