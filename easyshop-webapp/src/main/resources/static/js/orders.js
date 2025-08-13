document.addEventListener('alpine:init', () => {
    Alpine.data('initData', () => ({
        orders: [],
        init() {
            this.loadOrders();
            updateCartItemCount();
        },
        loadOrders() {
            $.getJSON(apiGatewayUrl+"/orders/api/orders", (data) => {
                console.log("Get orders :", data)
                this.orders = data
            });
        },
    }))
});