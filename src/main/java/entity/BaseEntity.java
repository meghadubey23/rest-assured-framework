package entity;

import Utilities.AssertUtility;

import java.util.List;

public abstract class BaseEntity {

    protected void verify(List<String[]> list) {
        for (String[] subList : list) {
            AssertUtility.assertEquals(subList[0], subList[1], subList[2]);
        }
        System.out.println("\n");
    }
}
