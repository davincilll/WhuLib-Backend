package com.dfcold.whulibbackend.config;

import com.dfcold.whulibbackend.domain.dto.RunConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunConfigTypeHandler extends BaseTypeHandler<RunConfig> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RunConfig parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 将 RunConfig 对象转换为 JSON 字符串
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting RunConfig to JSON", e);
        }
    }

    @Override
    public RunConfig getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return convertJsonToRunConfig(json);
    }

    @Override
    public RunConfig getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return convertJsonToRunConfig(json);
    }

    @Override
    public RunConfig getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return convertJsonToRunConfig(json);
    }

    private RunConfig convertJsonToRunConfig(String json) throws SQLException {
        if (json != null) {
            try {
                return objectMapper.readValue(json, RunConfig.class);
            } catch (JsonProcessingException e) {
                throw new SQLException("Error converting JSON to RunConfig", e);
            }
        }
        return null;
    }
}