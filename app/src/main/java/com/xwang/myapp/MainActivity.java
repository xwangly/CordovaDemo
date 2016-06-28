/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.xwang.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity
{
    @Bind(R.id.webView)
    WebView webView;

    private JsCallJava jsCallJava;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
//        Log.d("MainActivity", "launchUrl:"+launchUrl);
//        loadUrl(launchUrl);
        setContentView(R.layout.layout_main);

        ButterKnife.bind(this);


        jsCallJava = new JsCallJava();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//                return super.onJsPrompt(view, url, message, defaultValue, result);
                result.confirm(jsCallJava.call(view, message));
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/m/index.html");
    }
}
