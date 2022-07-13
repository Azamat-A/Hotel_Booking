package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.ReplyToReviewDto;
import kg.megacom.hotel_booking.models.dtos.ReplyToReviewDto.ReplyToReviewDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.models.entities.ReplyToReview;
import kg.megacom.hotel_booking.models.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:14+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class ReplyToReviewMapperImpl implements ReplyToReviewMapper {

    @Override
    public ReplyToReview replyToReviewDtoToReplyToReview(ReplyToReviewDto replyToReviewDto) {
        if ( replyToReviewDto == null ) {
            return null;
        }

        ReplyToReview replyToReview = new ReplyToReview();

        replyToReview.setId( replyToReviewDto.getId() );
        replyToReview.setText( replyToReviewDto.getText() );
        replyToReview.setUser( userDtoToUser( replyToReviewDto.getUser() ) );

        return replyToReview;
    }

    @Override
    public ReplyToReviewDto replyToReviewToReplyToReviewDto(ReplyToReview replyToReview) {
        if ( replyToReview == null ) {
            return null;
        }

        ReplyToReviewDtoBuilder replyToReviewDto = ReplyToReviewDto.builder();

        replyToReviewDto.id( replyToReview.getId() );
        replyToReviewDto.text( replyToReview.getText() );
        replyToReviewDto.user( userToUserDto( replyToReview.getUser() ) );

        return replyToReviewDto.build();
    }

    @Override
    public List<ReplyToReview> replyToReviewDtoListToReplyToReviewList(List<ReplyToReviewDto> reviewDtos) {
        if ( reviewDtos == null ) {
            return null;
        }

        List<ReplyToReview> list = new ArrayList<ReplyToReview>( reviewDtos.size() );
        for ( ReplyToReviewDto replyToReviewDto : reviewDtos ) {
            list.add( replyToReviewDtoToReplyToReview( replyToReviewDto ) );
        }

        return list;
    }

    @Override
    public List<ReplyToReviewDto> replyToReviewListToReplyToReviewDtoList(List<ReplyToReview> replyToReviews) {
        if ( replyToReviews == null ) {
            return null;
        }

        List<ReplyToReviewDto> list = new ArrayList<ReplyToReviewDto>( replyToReviews.size() );
        for ( ReplyToReview replyToReview : replyToReviews ) {
            list.add( replyToReviewToReplyToReviewDto( replyToReview ) );
        }

        return list;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        user.setRole( userDto.getRole() );
        user.setActive( userDto.isActive() );

        return user;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setRole( user.getRole() );
        userDto.setActive( user.isActive() );

        return userDto;
    }
}
