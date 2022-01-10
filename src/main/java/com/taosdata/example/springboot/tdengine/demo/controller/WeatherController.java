package com.taosdata.example.springboot.tdengine.demo.controller;

import com.taosdata.example.springboot.tdengine.demo.domain.Weather;
import com.taosdata.example.springboot.tdengine.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/weather")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * create database and table
     * 初始化
     * @return
     */
    @GetMapping("/init")
    public int init() {
        return weatherService.init();
    }

    /**
     * Pagination Query
     * 分页查询
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/{limit}/{offset}")
    public List<Weather> queryWeather(@PathVariable Long limit, @PathVariable Long offset) {
        return weatherService.query(limit, offset);
    }

    /**
     * upload single weather info
     * 新增天气数据
     * @param temperature
     * @param humidity
     * @return
     */
    @PostMapping("/{temperature}/{humidity}")
    public int saveWeather(@PathVariable float temperature, @PathVariable float humidity) {
        return weatherService.save(temperature, humidity);
    }


    /**
     * 批量插入
     * @param weatherList
     * @return
     */
    @PostMapping("/batch")
    public int saveBatchWeather(@RequestBody List<Weather> weatherList) {
        return weatherService.saveBatch(weatherList);
    }

    /**
     * 查询数量
     * @return
     */
    @GetMapping("/count")
    public int count() {
        return weatherService.count();
    }

    /**
     * 获取分表名称
     * @return
     */
    @GetMapping("/subTables")
    public List<String> getSubTables() {
        return weatherService.getSubTables();
    }

    @GetMapping("/avg")
    public List<Weather> avg() {
        return weatherService.avg();
    }

}
