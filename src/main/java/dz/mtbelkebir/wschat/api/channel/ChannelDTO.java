package dz.mtbelkebir.wschat.api.channel;

public record ChannelDTO(String title, String owner) {
    public static ChannelDTO from(Channel c) {
        return new ChannelDTO(c.getTitle(), c.getOwner().getUsername());
    }
}
