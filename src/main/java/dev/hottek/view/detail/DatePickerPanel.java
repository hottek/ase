package dev.hottek.view.detail;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DatePickerPanel extends JPanel {
    private Vector<String> dayList;
    private Vector<String> monthList;
    private Vector<String> yearList;
    private JComboBox dayInput;
    private JComboBox monthInput;
    private JComboBox yearInput;

    public DatePickerPanel() {
        generateComboBoxLists();

        this.dayInput = new JComboBox(this.dayList);
        this.monthInput = new JComboBox(this.monthList);
        this.yearInput = new JComboBox(this.yearList);

        setComboBoxValuesToCurrentDate();

        this.setLayout(new BorderLayout());
        this.add(this.dayInput, BorderLayout.WEST);
        this.add(this.monthInput, BorderLayout.CENTER);
        this.add(this.yearInput, BorderLayout.EAST);
    }

    public String getText() {
        String day = String.valueOf(this.dayInput.getSelectedItem());
        day = day.length() < 2 ? "0" + day : day;
        String month = String.valueOf(this.monthInput.getSelectedItem());
        month = month.length() < 2 ? "0" + month : month;
        String year = String.valueOf(this.yearInput.getSelectedItem());
        return year + month + day;
    }

    public long getTimestamp() {
        LocalDate date = LocalDate.parse(getText(), DateTimeFormatter.BASIC_ISO_DATE);
        return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    private void setComboBoxValuesToCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        this.dayInput.setSelectedIndex(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        this.monthInput.setSelectedIndex(calendar.get(Calendar.MONTH));
        this.yearInput.setSelectedIndex(this.yearInput.getItemCount() - 1);
    }

    private void generateComboBoxLists() {
        this.dayList = new Vector<>();
        for (int i = 1; i <= 31; i++) {
            this.dayList.add(String.valueOf(i));
        }
        this.monthList = new Vector<>();
        for (int i = 1; i <= 12; i++) {
            this.monthList.add(String.valueOf(i));
        }
        this.yearList = new Vector<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = (currentYear - 20); i <= currentYear; i++) {
            this.yearList.add(String.valueOf(i));
        }
    }
}
