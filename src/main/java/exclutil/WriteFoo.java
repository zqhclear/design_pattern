package exclutil;

import java.util.Date;

/**
 * @Author puzl
 * @Date 2017/10/24
 * @Description:
 */
public class WriteFoo {
    private String name;
    private Integer id;
    private Date date;

    public WriteFoo(Integer id, String name, Date date){
        this.id = id;
        this.name = name;
        this.date = date;

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
