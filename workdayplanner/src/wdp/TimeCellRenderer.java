/*
 * $Id$
 */
package wdp;

import java.text.DateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author Robson
 */
public class TimeCellRenderer extends DefaultTableCellRenderer {

    DateFormat formatter;

    public TimeCellRenderer() {
        super();
    }

    @Override
    public void setValue(Object value) {
        if (formatter == null) {
            formatter = DateFormat.getTimeInstance();
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}