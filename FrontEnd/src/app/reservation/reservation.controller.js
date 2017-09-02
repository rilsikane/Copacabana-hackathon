(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($scope,$stateParams,ApiService,$uibModal) {
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
    // $scope.slides = ["http://128.199.125.246:81/room-attach/2/1.png", "http://128.199.125.246:81/room-attach/2/3.png", "http://128.199.125.246:81/room-attach/2/2.png", "http://128.199.125.246:81/room-attach/2/5.png", "http://128.199.125.246:81/room-attach/2/4.png"]
  }
    
   })();