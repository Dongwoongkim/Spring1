package hello.core.order;

import hello.core.MemberRepository;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;

public class OrderServiceImp implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy dp;

    public OrderServiceImp(MemberRepository memberRepository, DiscountPolicy dp) {
        this.memberRepository = memberRepository;
        this.dp = dp;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = dp.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
