package com.mycom.myapp.dao;

import com.mycom.myapp.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource dataSource;

    @Override
    public int userRegister(UserDto userDto) {
        Connection con = null;
        PreparedStatement pstmt = null;

        int ret = -1;

        try {

            con = dataSource.getConnection();

            // 이미지, 가입일시는 테이블 컬럼 기본값으로 처리
            StringBuilder sb = new StringBuilder();
            sb.append("insert into users ( user_name, user_password, user_email ) ");
            sb.append("  values ( ?, ?, ? ) ");

            pstmt = con.prepareStatement(sb.toString());

            pstmt.setString(1, userDto.getUserName());
            pstmt.setString(2, userDto.getUserPassword());
            pstmt.setString(3, userDto.getUserEmail());

            ret = pstmt.executeUpdate();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if( pstmt != null ) pstmt.close();
                if( con != null ) con.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    @Override
    public UserDto login(String userEmail) {
        return null;
    }
}
