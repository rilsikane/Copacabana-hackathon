(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log,$rootScope) {
  // 	facebookConfig.init().then(function(){
	 //    console.log('Facebook SDK is loaded.');
	 // });
  // 	 facebookService.ready.then(function () {
  //     var statusHandler = function (response) {
  //       if (response.status === 'connected') {
  //         facebookService.api('/me').then(function (response) {
  //         	$rootScope.user = {};
  //           $rootScope.user.userId = response.id;
  //           $rootScope.user.userName = response.name;
  //            var pictureUrl = ($rootScope.user.userId) ? $rootScope.user.userId + "/picture" : "/me/picture";

	 //            var apiCall = facebookService.api(pictureUrl, {
	 //                redirect: false,
	 //                width: 40,
	 //                height: 40
	 //            });

	 //            apiCall.then(function (response) {
	 //                $rootScope.user.pictureUrl = response.data.url;
	 //            });
  //         });

  //       }
  //     };

  //     facebookService.Event.subscribe('auth.statusChange', statusHandler);
  //   });
  }

})();
