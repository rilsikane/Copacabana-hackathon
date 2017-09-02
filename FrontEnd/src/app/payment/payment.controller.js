(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('PaymentController', PaymentController);

  /** @ngInject */
  function PaymentController($scope,$http,ApiService,FileUploader,$rootScope,$state,msgDialog) {

      $scope.trans = [];
  	
  	$scope.uploadFile = function(reserveId,userNo){
      // alert("11");
      var param = {};
      param.reserveId = reserveId;
      // param.userNo = "13";
      param.userNo = $rootScope.user.userNo;
      ApiService.uploadFileToUrl(param,$scope.fileToUpload).then(function(result){
          console.log(result);
          msgDialog.success(undefined,"อัพโหลดสำเร็จ");
           $state.go("app.home");
      });


    }
    $scope.searchReserveTrans= function(){
        var param = {};
         // param.userNo = "12";
        param.userNo = $rootScope.user.userNo;

      ApiService.postService(param,"reservation.getReserveTrans.service").then(function(result){
        console.log(result);
        $scope.trans = result;
      })
    }
    
    $scope.searchReserveTrans();
    
  }

})();
