package gt.edu.umg.ingenieria.sistemas.laboratorio1.Ayuda;
import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

public class ayuda {
    public static boolean nAnios(Date birtDate, int N) {
    Calendar calendar = getCalendar(new Date());
    Calendar calendar1 = getCalendar(birtDate);
    int Diferencia = calendar.get(YEAR) - calendar1.get(YEAR);
    if
    (
            calendar1.get(MONTH) > calendar.get(MONTH) ||
                    (calendar1.get(MONTH) == calendar.get(MONTH) && calendar1.get(DATE) > calendar.get(DATE))
    )
    {
        Diferencia--;
    }
    return Diferencia >= N;
}

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
