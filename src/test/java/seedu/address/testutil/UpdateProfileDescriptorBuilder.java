package seedu.address.testutil;

import seedu.address.logic.commands.UpdateCommand;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Profile;

public class UpdateProfileDescriptorBuilder {

    private UpdateCommand.UpdateProfileDescriptor descriptor;

    public UpdateProfileDescriptorBuilder() {
        descriptor = new UpdateCommand.UpdateProfileDescriptor();
    }

    public UpdateProfileDescriptorBuilder(UpdateCommand.UpdateProfileDescriptor descriptor) {
        this.descriptor = new UpdateCommand.UpdateProfileDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateProfileDescriptor} with fields containing {@code profile}'s details
     */
    public UpdateProfileDescriptorBuilder(Profile profile) {
        descriptor = new UpdateCommand.UpdateProfileDescriptor();
        descriptor.setName(profile.getName());
        descriptor.setId(profile.getId());
        descriptor.setHeight(profile.getHeight());
        descriptor.setWeight(profile.getStartingWeight());
    }

    /**
     * Sets the {@code Name} of the {@code UpdateProfileDescriptor} that we are building.
     */
    public UpdateProfileDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code UpdateProfileDescriptor} that we are building.
     */
    public UpdateProfileDescriptorBuilder withId(String id) {
        descriptor.setId(new ID(id));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code UpdateProfileDescriptor} that we are building.
     */
    public UpdateProfileDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code UpdateProfileDescriptor} that we are building.
     */
    public UpdateProfileDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    public UpdateCommand.UpdateProfileDescriptor build() {
        return descriptor;
    }
}
