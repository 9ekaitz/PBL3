package chart;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ChartTableModel extends AbstractTableModel implements TableModel {

	private Object data[][];   

    public int getColumnCount(){   
        return 7;   
    }   

    public int getRowCount() {   
        return 1;   
    }   

    public Object getValueAt(int i, int j)   {   
        return data[i][j];   
    }   

    public void setValueAt(Object obj, int i, int j) {   
        data[i][j] = obj;   
        fireTableDataChanged();   
    }   

    public String getColumnName(int i)    {   
        switch(i) {   
        case 0: 
            return "Data:";   
		case 1:  
            return "Day:";   
        case 2:  
            return "Products:";   
        case 3:
            return "Day (prev)";   
        case 4:   
            return "Products (prev):";   
        case 5:  
            return "Day (next):";   
        case 6:   
            return "Products (next):";   
        }   
        return null;   
    }   

    public ChartTableModel(int i) {   
        data = new Object[i][7];   
    }   
}
