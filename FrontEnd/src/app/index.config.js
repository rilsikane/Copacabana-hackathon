(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .config(config);

  /** @ngInject */
  function config($logProvider, toastrConfig,uiGmapGoogleMapApiProvider) {

    // Enable log
    $logProvider.debugEnabled(true);

     // facebookConfigProvider.setAppId(272847096453655);
     //  facebookConfigProvider.setLanguage('en-US');
     //  facebookConfigProvider.setDebug(true);

     //  // When autoInit is setted to false you need to initialize
     //  // the facebookConfig service manually inside a run block.
     //  facebookConfigProvider.autoInit(falsez);

     //  // Same: developers.facebook.com/docs/javascript/reference/FB.init/
     //  facebookConfigProvider.setOptions({
     //    status: true
     //  });
    uiGmapGoogleMapApiProvider.configure({
      client : 'com.oms.items',
      v: '3.17',
      libraries: 'weather,geometry,visualization'
      key: 'AIzaSyAtVrNZfScv-DQ-sgbiguV8Ud0-UMoj9fI'
      transport: 'http'
      isGoogleMapsForWork: true
    });
  }

})();
