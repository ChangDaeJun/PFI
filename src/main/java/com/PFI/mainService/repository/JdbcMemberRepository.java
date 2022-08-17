package com.PFI.mainService.repository;

import com.PFI.mainService.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    public final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("USER").usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EMAIL", member.getUsername());
        parameters.put("PASSWORD", member.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.intValue());
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        List<Member> result = jdbcTemplate.query("select * from USER WHERE ID = ?", userRowMapper() ,id);
        return result.stream().findAny();
    }

    private RowMapper<Member> userRowMapper() {
        return ((rs, rowNum) -> {
           Member member = new Member();
           member.setId(rs.getInt("ID"));
           member.setUsername(rs.getString("EMAIL"));
           member.setPassword(rs.getString("PASSWORD"));
           return member;
        });
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> result = jdbcTemplate.query("select * from USER WHERE EMAIL = ?", userRowMapper() ,email);
        return result.stream().findAny();
    }

    @Override
    public Member update(Member member, String newPassword) {
        jdbcTemplate.update("update USER set PASSWORD = ? WHERE ID = ?", newPassword, member.getId());
        member.setPassword(newPassword);
        return member;
    }

    @Override
    public void delete(Member member) {
        jdbcTemplate.update("delete FROM USER WHERE ID = ?", member.getId());
    }
}
