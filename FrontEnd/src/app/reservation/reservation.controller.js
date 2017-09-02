(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($scope,$stateParams,ApiService,$uibModal,msgDialog,$rootScope,$state) {
  	console.log($stateParams.room);
  	// $scope.detail = [];
  	$scope.detail = $stateParams.room;
  	$scope.slides = [];
  	ApiService.postService($stateParams.room,"attach.searchRoomAttach.service").then(function(result){
	    
  		for(var i=0;i<result.length;i++){
  			var attach = result[i];
  			$scope.slides.push("http://128.199.125.246:81"+attach.attachPath);
  		}
	    console.log($scope.slides);
	 })
    $scope.imageClick = function(img){
      $scope.selectImg = img;
      var modalInstance = $uibModal.open({
          animation: true,
          templateUrl: 'imagePopup.html',
          size: 'lg',
          scope: $scope
      });
    }
    $scope.reserveRoom = function(){
      var param = {};
      param.roomId = $scope.detail.roomId;
      param.userNo = $rootScope.user.userNo;
      param.approveStatus = "N";
      msgDialog.confirm(undefined,'ยืนยันการจองใช่หรือไม่?',function(){
         ApiService.postService(param,"reservation.reserveRoom.service").then(function(result){
           msgDialog.success(undefined,"จองสำเร็จ");
           $state.go("app.search")
         });
     });
   }
    // $scope.slides = ["http://128.199.125.246:81/room-attach/2/1.png", "http://128.199.125.246:81/room-attach/2/3.png", "http://128.199.125.246:81/room-attach/2/2.png", "http://128.199.125.246:81/room-attach/2/5.png", "http://128.199.125.246:81/room-attach/2/4.png"]
  }
    
   })();