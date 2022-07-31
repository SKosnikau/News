package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class DataBaseStub {
    private List<NewUserInfo> dataBase = new ArrayList<>();
    private static final DataBaseStub instance = new DataBaseStub();

    public DataBaseStub() {
    }

    public DataBaseStub(List<NewUserInfo> database) {
        instance.dataBase = dataBase;
    }

    public static DataBaseStub getInstance() {
        return instance;
    }

    public List<NewUserInfo> getDataBase() {
        NewUserInfo userUser = new NewUserInfo("Petr", "Petrov", "abc", "12345", "PPetrov@mail.uk", "user");
        NewUserInfo userAdmin = new NewUserInfo("Ivan", "Ivanov", "cxz", "qwerty", "ivanov@mail.cn", "admin");
        dataBase.add(userUser);
        dataBase.add(userAdmin);

        return dataBase;
    }

    @Override
    public String toString() {
        return "DataBaseStub{" +
                "dataBase=" + dataBase +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DataBaseStub that = (DataBaseStub) obj;
        return Objects.equal(dataBase, that.dataBase);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dataBase);
    }
}