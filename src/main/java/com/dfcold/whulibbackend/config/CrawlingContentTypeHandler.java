package com.dfcold.whulibbackend.config;

import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrawlingContentTypeHandler extends BaseTypeHandler<CrawlingContent> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CrawlingContent parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 将 CrawlingContent 对象转换为 JSON 字符串
            String json = OBJECT_MAPPER.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            throw new SQLException("无法将 CrawlingContent 转换为 JSON", e);
        }
    }

    @Override
    public CrawlingContent getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return convertJsonToCrawlingContent(json);
    }

    @Override
    public CrawlingContent getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return convertJsonToCrawlingContent(json);
    }

    @Override
    public CrawlingContent getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return convertJsonToCrawlingContent(json);
    }

    private CrawlingContent convertJsonToCrawlingContent(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            // 将 JSON 字符串转换回 CrawlingContent 对象
            return OBJECT_MAPPER.readValue(json, CrawlingContent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("无法将 JSON 转换为 CrawlingContent", e);
        }
    }
}