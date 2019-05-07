package Infomation;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Set;
import java.util.TreeSet;

public class Item extends Data implements Serializable {
    private String title, desc;
    private Set<String> keywords;
    private int id;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Item(String title, String desc, Set<String> keywords) {
        this.title = title;
        this.desc = desc;
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean isData(Data i) {
        if (i instanceof Item) {
            return title.equals(((Item) i).getTitle());
        }
        return false;
    }

    @Override
    public DataType getDataType() {
        return DataType.Item;
    }

    @Override
    public boolean matches(Data i) {
        if (i == null) {
            return true;
        }else if (i instanceof Item) {
            Item item = (Item) i;
            Set<String> union = new TreeSet<>(item.getKeywords());
            union.retainAll(keywords);

            if (item.getTitle().equals(title) ||
                    item.getDesc().equals(desc) ||
                    !union.isEmpty() ||
                    item.getId() == id) {
                return  true;
            }
        }
        return false;
    }

    public String toString() {
        return "Title: " + title +
                "\n  Description: "  + desc +
                "\n  Keywords:  " + keywords;
    }
}
