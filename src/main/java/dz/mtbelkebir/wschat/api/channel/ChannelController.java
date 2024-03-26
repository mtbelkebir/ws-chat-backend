package dz.mtbelkebir.wschat.api.channel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dz.mtbelkebir.wschat.api.util.GenericResponse;
import lombok.RequiredArgsConstructor;




import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/channels")
public class ChannelController {

    private final ChannelService channelService;
    
    @PostMapping
    public ResponseEntity<?> createChannel(@RequestBody NewChannelDTO request) {
        ChannelDTO c = channelService.createNewChannel(request);

        return ResponseEntity.ok().body(GenericResponse.builder().message("Channel created successfully !").data(c).build());
    }
    

    
}
