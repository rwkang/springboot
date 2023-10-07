/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package shop.onekorea.springboot.mapper.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * Handler for UUID types.
 *
 * @see UUID
 */
@MappedTypes({UUID.class})
@Slf4j
public class UuidTypeHandler implements TypeHandler<UUID> {
    private static final Logger LOG = LoggerFactory.getLogger(UuidTypeHandler.class);

    @Override
    public void setParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setObject(i, null, Types.OTHER);
        } else {
            ps.setObject(i, parameter.toString(), Types.OTHER);
        }

    }

    @Override
    public UUID getResult(ResultSet rs, String columnName) throws SQLException {
        return toUUID(rs.getString(columnName));
    }

    @Override
    public UUID getResult(ResultSet rs, int columnIndex) throws SQLException {
        return toUUID(rs.getString(columnIndex));
    }

    @Override
    public UUID getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toUUID(cs.getString(columnIndex));
    }

    private static UUID toUUID(String val) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

        System.err.println("UUID: " + val);

        // 문자열로 변환하기 전 타입 확인
        System.err.println(val.getClass().getTypeName());

//        val = String(val.getBytes("8859_1"), "UTF-8");

        // UUID를 문자열로 변환
        System.err.println(val.toString());

        // 문자열로 변환 후 타입 확인
        System.err.println(val.toString().getClass().getTypeName());

        if (!Strings.isNullOrEmpty(val)) {
            try {
                return UUID.fromString(val);
            } catch (IllegalArgumentException e) {
                LOG.warn("Bad UUID found: {}", val);
            }
        }
        return null;
    }

}