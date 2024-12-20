package com.onetool.server.api.order.dto.response;

import com.onetool.server.api.blueprint.Blueprint;
import com.onetool.server.api.blueprint.dto.BlueprintResponse;
import com.onetool.server.api.member.domain.Member;
import com.onetool.server.api.member.dto.MemberSimpleInfoDto;
import com.onetool.server.api.order.Orders;
import lombok.Builder;

import java.util.List;

public class OrderResponse {

    @Builder
    public record OrderPageMemberResponseDto(
            Long totalPrice,
            MemberSimpleInfoDto memberInfo,
            List<BlueprintResponse> items
    ){
        public static OrderPageMemberResponseDto orderPage(Long totalPrice, Member member, List<Blueprint> diabetes){
            return OrderPageMemberResponseDto.builder()
                    .totalPrice(totalPrice)
                    .memberInfo(MemberSimpleInfoDto.makeMemberSimpInfoDto(member))
                    .items(diabetes.stream().map(BlueprintResponse::items).toList())
                    .build();
        }
    }

    @Builder
    public record OrderCompleteResponseDto(
            Long totalPrice,
            List<BlueprintResponse> blueprints
    ){
        public static OrderCompleteResponseDto response(Orders orders){
            return OrderCompleteResponseDto.builder()
                    .totalPrice(orders.getTotalPrice())
                    .blueprints(orders.getOrderItems()
                            .stream()
                            .map(orderBlueprint ->
                                    BlueprintResponse.items(orderBlueprint.getBlueprint()))
                            .toList())
                    .build();
        }
    }
}