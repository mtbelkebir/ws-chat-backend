package dz.mtbelkebir.wschat.api.channel;

import java.util.HashSet;


import dz.mtbelkebir.wschat.api.exception.ChannelNotFoundException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dz.mtbelkebir.wschat.api.user.User;
import dz.mtbelkebir.wschat.api.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public ChannelDTO createNewChannel(NewChannelDTO newChannelDto) {
        User loggedUser = userRepository.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new IllegalStateException());

        Channel newChannel = new Channel();
        newChannel.setTitle(newChannelDto.title());
        newChannel.setOwner(loggedUser);
        newChannel.setMembers(new HashSet<>());
        newChannel.getMembers().add(loggedUser);
        newChannel = channelRepository.save(newChannel);
        return ChannelDTO.from(newChannel);
    }

    public void joinChannel(Long channelId) throws ChannelNotFoundException{
        Channel c = channelRepository.findById(channelId).orElseThrow(ChannelNotFoundException::new);

        User loggedUser = userRepository.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new IllegalStateException());
        c.getMembers().add(loggedUser);
        channelRepository.save(c);
    }

}
