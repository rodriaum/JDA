/*
 * Copyright 2015 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.api.events.emoji.update;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.emoji.GenericEmojiEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Indicates that an {@link RichCustomEmoji Custom Emoji} was updated.
 *
 * <p><b>Requirements</b><br>
 *
 * <p>These events require the {@link net.dv8tion.jda.api.utils.cache.CacheFlag#EMOJI EMOJI} CacheFlag to be enabled, which requires
 * the {@link net.dv8tion.jda.api.requests.GatewayIntent#GUILD_EXPRESSIONS GUILD_EXPRESSIONS} intent.
 *
 * <br>{@link net.dv8tion.jda.api.JDABuilder#createLight(String) createLight(String)} disables that CacheFlag by default!
 */
public abstract class GenericEmojiUpdateEvent<T> extends GenericEmojiEvent implements UpdateEvent<RichCustomEmoji, T>
{
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericEmojiUpdateEvent(
            @Nonnull JDA api, long responseNumber, @Nonnull RichCustomEmoji emoji,
            @Nullable T previous, @Nullable T next, @Nonnull String identifier)
    {
        super(api, responseNumber, emoji);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Nonnull
    @Override
    public RichCustomEmoji getEntity()
    {
        return getEmoji();
    }

    @Nonnull
    @Override
    public String getPropertyIdentifier()
    {
        return identifier;
    }

    @Nullable
    @Override
    public T getOldValue()
    {
        return previous;
    }

    @Nullable
    @Override
    public T getNewValue()
    {
        return next;
    }
}
