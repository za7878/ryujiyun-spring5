    INSERT INTO tbl_member (
        user_id,
        user_pw,
        user_name,
        email,
        point,
        enabled,
        levels,
        reg_date
    ) VALUES (
        'user_del',
        '1234',
        '삭제할 사용자',
        'user@test,com',
        10,
        1,
        'ROLE_USER',
        systimestamp
    )