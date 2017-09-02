(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('MainController', MainController)
    .controller('ModalInstanceCtrl', ModalInstanceCtrl);

  /** @ngInject */
  function MainController($uibModal,$scope) {

      $scope.login = function(){
         var modalInstance = $uibModal.open({
          animation: true,
          templateUrl: 'myModalContent.html',
          controller: 'ModalInstanceCtrl',
          size: 'lg'
        });

      
    }
    $scope.init = function(){
      $scope.map = { center: { latitude: 13.821485, longitude: 100.307497 }, zoom: 15 };
    }

    $scope.init();
    
  }
   function ModalInstanceCtrl(){
    }
})();
