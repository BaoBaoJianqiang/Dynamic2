package jianqiang.com.plugin1;

import com.example.jianqiang.mypluginlibrary.IBean;

public class Bean implements IBean {
    private String name = "jianqiang";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String paramString) {
        this.name = paramString;
    }
}
