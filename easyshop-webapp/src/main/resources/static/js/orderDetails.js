document.addEventListener('alpine:init', () => {
    Alpine.data('initData', (orderNumber) => ({
        orderNumber: orderNumber,
        orderDetails: {
            items: [],
            customer: {},
            deliveryAddress: {}
        },
        init() {
            updateCartItemCount();
            this.getOrderDetails(this.orderNumber)
        },
        getOrderDetails(orderNumber) {
            console.log("Get Order by orderNumber URL:", "http://localhost:8989/orders/api/orders/"+ orderNumber)
            $.getJSON(apiGatewayUrl+"/orders/api/orders/"+ orderNumber, (data) => {
                console.log("Get Order by orderNumber Resp:", data)
                this.orderDetails = data
            });
        }
    }))
});