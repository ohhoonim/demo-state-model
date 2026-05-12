package dev.ohhoonim.post.model;

import dev.ohhoonim.components.model.state.StateTransitionPolicy;

public interface PostTransitionPolicy extends StateTransitionPolicy<PostStatus, PostTransitionEvent, Post>{
    
}
