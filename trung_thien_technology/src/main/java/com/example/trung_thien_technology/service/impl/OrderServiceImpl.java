package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.*;
import com.example.trung_thien_technology.projection.IOrderProjection;
import com.example.trung_thien_technology.repository.IOrderDetailRepository;
import com.example.trung_thien_technology.repository.IOrderRepository;
import com.example.trung_thien_technology.repository.IProductRepository;
import com.example.trung_thien_technology.service.IOrderDetailService;
import com.example.trung_thien_technology.service.IOrderService;
import com.example.trung_thien_technology.util.RandomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;

    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public void saveOrder(List<OrderDetailDTO> orderDetailDTOS, Customers customers) {
        Integer orderCode = RandomCode.randomBookCode(this.iOrderRepository.findAll().stream().map(Orders::getOrderCode).collect(Collectors.toList()));
        long totalPrice = 0L;
//        int newQuantity = 0;
//        for (int i = 0; i < orderDetailDTOS.size(); i++) {
//            totalPrice += orderDetailDTOS.get(i).getQuantity() * orderDetailDTOS.get(i).getPrice();
//            if (Objects.equals(orderDetailDTOS.get(i).getId(), productsList.get(i).getId()))
//                newQuantity = productsList.get(i).getQuantity() - orderDetailDTOS.get(i).getQuantity();
//            this.iProductRepository.updateQuantity(orderDetailDTOS.get(i).getId(), newQuantity);
//        }
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            totalPrice += orderDetailDTO.getQuantity() * orderDetailDTO.getPrice();
        }
        Orders orders = new Orders(customers);
        orders.setOrderCode(orderCode);
        PaymentTypes paymentTypes = new PaymentTypes();
        paymentTypes.setId(1);
        orders.setPaymentTypes(paymentTypes);
        orders.setTotalPrice(totalPrice);
        this.iOrderRepository.save(orders);
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            this.iOrderDetailRepository.save(new OrderDetails(new Products(orderDetailDTO.getId()), orders, orderDetailDTO.getPrice(), orderDetailDTO.getQuantity()));
            this.iProductRepository.updateQuantityById(orderDetailDTO.getTotalQuantity()-orderDetailDTO.getQuantity(),orderDetailDTO.getId());
        }
        String to = customers.getEmail();
        String subject = "Bạn có đơn hàng từ Trung Thiện Technology";
        String body = "<h6>Chào " + customers.getName() + ",</p>\n" +
                "\n" +
                "<p>Chúng tôi gửi mail này để xác nhận rằng bạn vừa đặt hàng thành công từ Trung Thiện Technology </p>\n" +
                "\n" +
                "<p>Đây là mã đơn hàng của bạn của bạn: OD-" + orderCode + "</p>\n";

        body += "\nChúng tôi xin cảm ơn quý khách đã tin tường và sử dụng dịch vụ của chúng tôi.\n" +
                "---------------------------------------" + "\n" +
                "Name: rung Thiện Technology\n" +
                "Mobile: 0338410349\n" +
                "Email: thien7028@gmail.com\n" +
                "Address: 280 Trần Hưng Đạo,Sơn Trà,Đà nẵng";
        emailService.sendMail(to, subject, body);
    }

    @Override
    @Transactional
    public void saveOrderByPayPal(List<OrderDetailDTO> orderDetailDTOS, Customers customers) {
        Integer orderCode = RandomCode.randomBookCode(this.iOrderRepository.findAll().stream().map(Orders::getOrderCode).collect(Collectors.toList()));
        long totalPrice = 0L;
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            totalPrice += orderDetailDTO.getQuantity() * orderDetailDTO.getPrice();
        }
        Orders orders = new Orders(customers);
        orders.setOrderCode(orderCode);
        orders.setTotalPrice(totalPrice);
        PaymentTypes paymentTypes = new PaymentTypes();
        paymentTypes.setId(2);
        orders.setPaymentTypes(paymentTypes);
        orders.setPaymentStatus(true);
        this.iOrderRepository.save(orders);
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            this.iOrderDetailRepository.save(new OrderDetails(new Products(orderDetailDTO.getId()), orders, orderDetailDTO.getPrice(), orderDetailDTO.getQuantity()));
            this.iProductRepository.updateQuantityById(orderDetailDTO.getTotalQuantity()-orderDetailDTO.getQuantity(),orderDetailDTO.getId());
        }
        String to = customers.getEmail();
        String subject = "Bạn có đơn hàng từ Trung Thiện Technology";
        String body = "<h6>Chào " + customers.getName() + ",</p>\n" +
                "\n" +
                "<p>Chúng tôi gửi mail này để xác nhận rằng bạn vừa thanh toán một đơn hàng thành công từ Trung Thiện Technology </p>\n" +
                "\n" +
                "<p>Đây là mã đơn hàng của bạn của bạn: OD-" + orderCode + "</p>\n";

        body += "\nChúng tôi xin cảm ơn quý khách đã tin tường và sử dụng dịch vụ của chúng tôi.\n" +
                "---------------------------------------" + "\n" +
                "Name: rung Thiện Technology\n" +
                "Mobile: 0338410349\n" +
                "Email: thien7028@gmail.com\n" +
                "Address: 280 Trần Hưng Đạo,Sơn Trà,Đà nẵng";
        emailService.sendMail(to, subject, body);
    }

    @Override
    public Page<IOrderProjection> findAllOrder(Pageable pageable, Integer customerId) {
        return this.iOrderRepository.findAllByCreateTime(pageable, customerId);
    }


}