package com.nemezis;

import com.vaadin.annotations.JavaScript;
import  org.vaadin.highcharts.AbstractHighChart;

/**
 *
 * @author Nemezis
 *
 *  Подключение jquery и hightcharts
 *
 */
@JavaScript({"vaadin://jquery/jquery-3.3.1.min.js", "vaadin://highcharts/highcharts.js", "vaadin://highcharts-connector.js"})
public class HighChart extends AbstractHighChart {
    private static final long serialVersionUID = -7326315426217377753L;
}
