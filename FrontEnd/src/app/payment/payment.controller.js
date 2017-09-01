(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('PaymentController', PaymentController);

  /** @ngInject */
  function PaymentController($scope,$http,ApiService,FileUploader) {
  	
  	$scope.uploadFile = function(){
      ApiService.uploadFileToUrl($scope.fileToUpload);
    };
    
    
    
  }

})();
