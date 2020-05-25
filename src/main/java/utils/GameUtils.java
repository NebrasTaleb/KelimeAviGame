package utils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.List;
import javax.swing.DefaultListModel;

public class GameUtils {

    public static void copyListToListExceptRemovedItem(List srcList, DefaultListModel dstList, int indexOfRemovedItem) {
        for (int index = 0; index < srcList.size(); index++) {
            if (index == indexOfRemovedItem) {
                continue;
            }
            dstList.addElement((String) srcList.get(index));
        }
    }
    
     public static void copyListToList(List srcList, List dstList) {
        for (int index = 0; index < srcList.size(); index++) {
            dstList.add((String) srcList.get(index));
        }
    }
}
