(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log,$rootScope,facebookConfig,facebookService) {
  	facebookConfig.init().then(function(){
	    console.log('Facebook SDK is loaded.');
	 });
    

  }

})();
