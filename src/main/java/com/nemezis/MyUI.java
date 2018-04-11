package com.nemezis;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Random;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    // Элементы
    public HighChart chart;
    public Button button;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        button = new Button("Генерировать");
        button.addClickListener( e -> {
            // layout.addComponent(new Label("Thanks " + name.getValue()
            //        + ", it works!"));

            // Create and configure a chart.
            chart = new HighChart();

            String JSchart = "var options = {" +
                    "                title: { " +
                    "                text: 'Тестовый график от Nemezis' " +
                    "                }, " +
                    "                xAxis: {" +
                    "                        categories: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек']" +
                    "                    }, " +
                    "                series: [{" +
                    "                name:'Факт', " +
                    "                data: [" ;

            // Генерируем случайные значения
            Random rn = new Random();
            int fact[] = new int[12];

            for (int i =0; i<12; i++ ) {
                int answer = rn.nextInt(90 - 50 + 1) + 50;
                fact[i]= answer;
                JSchart += String.valueOf(answer) + "," ;
            }

            // Обрезвем строку
            JSchart = JSchart.substring(0, JSchart.length() -1);

            JSchart +=         "]" +
                    "                },{" +
                    "                 name:'Очищенный факт'," +
                    "                data:[" ;

            for (int i =0; i<12; i++ ) {
                int answer = rn.nextInt(10) + 1;
                JSchart += String.valueOf( fact[i] - answer) + "," ;
            }

            // Обрезвем строку
            JSchart = JSchart.substring(0, JSchart.length() -1);

            JSchart +=        "]" +
                    "                }" +
                    "                ] };";

            chart.setHcjs(JSchart);

            layout.removeAllComponents();
            layout.addComponents(button, chart);

        });

        layout.addComponents(button);
        layout.setMargin(true);
        layout.setSpacing(true);

/*
        ScriptEngine js = new ScriptEngineManager().getEngineByName("javascript");
        try {
            js.eval(" console.log('TESTTTTT')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

*/
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
