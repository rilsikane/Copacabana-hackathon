(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .config(config);

  /** @ngInject */
  function config($logProvider, toastrConfig,facebookConfigProvider) {

    // Enable log
    $logProvider.debugEnabled(true);

     facebookConfigProvider.setAppId(272847096453655);
      facebookConfigProvider.setLanguage('en-US');
      facebookConfigProvider.setDebug(true);

      // When autoInit is setted to false you need to initialize
      // the facebookConfig service manually inside a run block.
      facebookConfigProvider.autoInit(false);

      // Same: developers.facebook.com/docs/javascript/reference/FB.init/
      facebookConfigProvider.setOptions({
        status: true
      });
  }

})();
