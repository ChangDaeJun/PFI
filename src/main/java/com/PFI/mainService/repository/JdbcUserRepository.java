package com.PFI.mainService.repository;

import com.PFI.mainService.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    public final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("USER").usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EMAIL", user.getEmail());
        parameters.put("PASSWORD", user.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.intValue());
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> result = jdbcTemplate.query("select * from USER WHERE ID = ?", userRowMapper() ,id);
        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return ((rs, rowNum) -> {
           User user = new User();
           user.setId(rs.getInt("ID"));
           user.setEmail(rs.getString("EMAIL"));
           user.setPassword(rs.getString("PASSWORD"));
           return user;
        });
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = jdbcTemplate.query("select * from USER WHERE EMAIL = ?", userRowMapper() ,email);
        return result.stream().findAny();
    }

    @Override
    public User update(User user, String newPassword) {
        jdbcTemplate.update("update USER set PASSWORD = ? WHERE ID = ?", newPassword, user.getId());
        user.setPassword(newPassword);
        return user;
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update("delete FROM USER WHERE ID = ?", user.getId());
    }
}
