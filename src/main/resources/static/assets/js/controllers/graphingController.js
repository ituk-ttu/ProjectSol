app.controller("graphingController", ["$q", "$scope", function ($q, $scope) {
    $scope.loading = false;

    $scope.calculate = function (panelSettings) {
        $scope.loading = true;
    }
}]);
